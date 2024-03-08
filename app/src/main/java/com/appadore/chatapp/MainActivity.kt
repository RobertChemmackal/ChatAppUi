package com.appadore.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appadore.chatapp.models.ChatListModel
import com.appadore.chatapp.models.ImageModel
import com.appadore.chatapp.ui.theme.BackGroundToolBar
import com.appadore.chatapp.ui.theme.ChatAppTheme


class MainActivity : ComponentActivity() {
    var mChatList = ArrayList<ChatListModel>()
    var mImageList = ArrayList<ImageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                Column {
                    Scaffold(
                        topBar = {
                            TopApparView()
                        },
                        floatingActionButton = {
                            Box(modifier = Modifier.padding(bottom = 90.dp)){
                                FloatingActionButton(
                                    onClick = {
                                        val intent = Intent(
                                            this@MainActivity,
                                            VideoTransperenntActivity::class.java
                                        )
                                        startActivity(intent)
                                    }, modifier = Modifier
                                        .size(60.dp)
                                        .background(BackGroundToolBar, CircleShape)
                                        .padding(
                                            start = 12.dp,
                                            end = 12.dp,
                                            top = 12.dp,
                                            bottom = 12.dp
                                        )
                                ) {
                                    Image(
                                        painterResource(R.drawable.ic_ggift),
                                        "",
                                        modifier = Modifier.size(50.dp)
                                    )

                                }

                            }
                        }
                    ) { innerPaddingValues ->
                        DashBoardContaier(innerPaddingValues)
                    }


                }


            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DashBoardContaier(innerPaddingValues: PaddingValues) {
        createLazyColumnChatList()
        Column(
            Modifier
                .background(Color.White)
                .padding(innerPaddingValues)) {
            Box(modifier = Modifier.weight(1f)) {
                Row {
                    Box(modifier = Modifier.weight(.9f)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = null,
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(10.dp))

                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Box(modifier = Modifier.weight(1.1f)) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                start = 8.dp,
                                top = 8.dp,
                                end = 8.dp,
                                bottom = 8.dp
                            ),
                            content = {
                                items(mImageList.size) { index ->
                                    Image(
                                        painter = painterResource(mImageList[index].image),
                                        contentDescription = null,
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .width(120.dp)
                                            .height(120.dp)
                                            .padding(4.dp)
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            )

                                    )
                                }
                            }
                        )
                    }

                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(BackGroundToolBar),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(
                        text = "Comments",
                        color = Color.White,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xffD3D3D3))
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    LazyColumn(
                        Modifier
                            .wrapContentSize()
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp), verticalArrangement = Arrangement.Center
                    ) {
                        items(mChatList) { chat ->
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_profile),
                                    contentDescription = null,
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .border(0.dp, Color.LightGray, CircleShape)
                                        .clip(CircleShape)

                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                    Text(
                                        text = chat.name,
                                        color = Color.Black,
                                        fontStyle = FontStyle.Normal,
                                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                                    )

                                }
                                Spacer(modifier = Modifier.size(16.dp))
                                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                    Text(
                                        text = chat.msg,
                                        color = Color(0xFf80000000),
                                        fontStyle = FontStyle.Normal,
                                        fontSize = 14.sp, fontWeight = FontWeight.Normal
                                    )

                                }
                            }


                        }
                    }
                }

            }
            Box(Modifier.padding(start = 12.dp,end=12.dp, bottom = 12.dp,top=12.dp)){

                Row {
                     Box(modifier = Modifier
                         .weight(1f)
                         .height(60.dp)
                         .fillMaxWidth()){
                         var text by remember { mutableStateOf(TextFieldValue("")) }
                         TextField(
                             modifier = Modifier
                                 .fillMaxHeight()
                                 .fillMaxWidth()
                                 .background(
                                     Color(0xffD9D9D9)
                                 ),
                             colors = TextFieldDefaults.textFieldColors(
                                 containerColor = Color(
                                     0xffD9D9D9
                                 ), focusedTextColor = Color.Black, focusedIndicatorColor = Color.Transparent,
                                 disabledIndicatorColor = Color.Transparent,
                                 unfocusedIndicatorColor = Color.Transparent,
                             ),
                             value = text,
                             onValueChange = {
                                 text = it
                             },
                             placeholder = { Text(text = "Send message") },
                         )

                     }
                    Spacer(modifier = Modifier.size(16.dp))
                    Image(

                        painterResource(R.drawable.baseline_send_24),
                        "",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterVertically)
                    )
                }

            }

        }
    }

    @Composable
    fun TopApparView() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackGroundToolBar)
                .height(100.dp)
        ) {
            Column(
                Modifier
                    .padding(12.dp)
            ) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .border(0.dp, Color.LightGray, CircleShape)
                            .clip(CircleShape)

                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = "Neymer Jr",
                            color = Color.White,
                            fontStyle = FontStyle.Normal,
                            fontSize = 18.sp, fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Host",
                            color = Color.White,
                            fontStyle = FontStyle.Normal,
                            fontSize = 16.sp, fontWeight = FontWeight.W500
                        )
                    }
                    Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
                        Image(

                            painterResource(R.drawable.ic_menu_vertical),
                            "",
                            modifier = Modifier
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically)
                        )

                    }
                }


            }

        }

    }

    private fun createLazyColumnChatList() {
        mChatList.add(ChatListModel(R.drawable.ic_profile, "Neymer Jr", "Sent a gift to Messi"))
        mChatList.add(ChatListModel(R.drawable.ic_profile, "Neymer Jr", "Sent a gift to Messi"))
        mChatList.add(ChatListModel(R.drawable.ic_profile, "Neymer Jr", "Sent a gift to Messi"))
        mChatList.add(ChatListModel(R.drawable.ic_profile, "Neymer Jr", "Sent a gift to Messi"))
        mChatList.add(ChatListModel(R.drawable.ic_profile, "Neymer Jr", "Sent a gift to Messi"))
        mChatList.add(ChatListModel(R.drawable.ic_profile, "Neymer Jr", "Sent a gift to Messi"))
        mChatList.add(ChatListModel(R.drawable.ic_profile, "Neymer Jr", "Sent a gift to Messi"))


        mImageList.add(ImageModel(R.drawable.ic_profile_one))
        mImageList.add(ImageModel(R.drawable.ic_proffile_two))
        mImageList.add(ImageModel(R.drawable.ic_profile))
        mImageList.add(ImageModel(R.drawable.ic_profile_one))
        mImageList.add(ImageModel(R.drawable.ic_proffile_two))
        mImageList.add(ImageModel(R.drawable.ic_profile))

    }

}

