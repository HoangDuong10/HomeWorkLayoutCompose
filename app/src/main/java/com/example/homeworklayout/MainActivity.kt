package com.example.homeworklayout
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.homeworklayout.model.User
import com.example.homeworklayout.ui.theme.HomeWorkLayoutTheme
import java.time.LocalTime
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeWorkLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeWorkLayoutTheme {
       MainScreen()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(){
    Column(modifier = Modifier
        .background(color = colorResource(id = R.color.grey))
        .padding(getPaddingScreen())) {
        Header()
        TabLayout()
    }
}

@Composable
fun Header(){
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
      ) {
        val (ivBanner,row,ivBack,tvTitle) = createRefs()
        Image(painter = painterResource(id = R.drawable.ic_banner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .constrainAs(ivBanner) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            contentScale = ContentScale.Crop)
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(bottom = getPaddingScreen(), start = getPaddingScreen())
            .constrainAs(row) {
                bottom.linkTo(ivBanner.bottom)
                start.linkTo(ivBack.end)
            }) {
            Icon(imageVector = Icons.Filled.Visibility,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = "12K",
                color = Color.White,
                fontSize = getNormalTextSize(),
                modifier = Modifier.padding(4.dp)
            )
            Icon(imageVector = Icons.Filled.ThumbUp,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(start = getPaddingScreen()),
            )
            Text(
                text = "10,5K",
                color = Color.White,
                fontSize = getNormalTextSize(),
                modifier = Modifier.padding(4.dp)
            )
            Icon(painter = painterResource(id = R.drawable.ic_comment) ,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(36.dp)
                    .padding(start = getPaddingScreen()),
            )
            Text(
                text = "12K",
                color = Color.White,
                fontSize = getNormalTextSize(),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Text(text = "Chiến dịch livestream 20/07/2024",
            fontSize = getXnormalTextSize(),
            color = Color.White,
            modifier = Modifier
                .padding(start = getPaddingScreen())
                .constrainAs(tvTitle) {
                    bottom.linkTo(row.top)
                    start.linkTo(ivBack.end)
                }
        )
        Icon(imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(start = getPaddingScreen(), bottom = getPaddingScreen())
                .size(24.dp)
                .constrainAs(ivBack) {
                    start.linkTo(parent.start)
                    top.linkTo(tvTitle.top)
                    bottom.linkTo(row.bottom)
                })
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(){
    val tabItems = listOf("Chốt đơn","Sản phẩm","Hiệu quả","Kịch bản")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    Column(modifier = Modifier) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                Box(modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(2.dp)
                    .padding(horizontal = 16.dp)
                    .background(color = colorResource(id = R.color.green)),
                )
            },
        ) {
            tabItems.forEachIndexed { index, tabItem ->
                val select = pagerState.currentPage == index
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = if (index == tabItems.size - 1) 2.dp else 0.dp,
                                    color = if (index == tabItems.size - 1) colorResource(id = R.color.green) else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(if (index == tabItems.size - 1) 6.dp else 0.dp,),
                        ) {
                            Text(
                                text = tabItem,
                                color = if (select|| index== tabItems.size-1) colorResource(id = R.color.green) else Color.Black,
                                fontSize = 12.sp
                            )
                        }
                    },
                )
            }
        }

        HorizontalPager(state = pagerState, userScrollEnabled = false) {page ->
            when(page){
                0-> TabLayoutOrder()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutOrder() {
        Column(
            modifier = Modifier
            .fillMaxWidth().background(Color.White).padding(top = 8.dp, bottom = 8.dp)
        ) {
            val tabItems = listOf("Chưa chốt", "Đã chốt", "Lên đơn")
            var selectedTabIndex by remember { mutableIntStateOf(0) }
            val pagerState = rememberPagerState { tabItems.size }
            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }

            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                divider = {},
                indicator = { Box {} },
                edgePadding = 0.dp,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                         // Adjust padding as needed
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(36.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Row(
                        modifier = Modifier
                            .height(40.dp)
                            .border(width = 1.dp, Color.Gray, RoundedCornerShape(50.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(36.dp)
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .width(0.3.dp)
                            .background(color = Color.Gray)
                            .padding(vertical = 12.dp)
                    )
                }
                tabItems.forEachIndexed { index, tabItem ->
                    val isSelect = pagerState.currentPage == index
                    Tab(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(if (isSelect) colorResource(id = R.color.green) else Color.White)
                            .height(36.dp)
                            .border(width = 1.dp, color = Color.Gray, shape = CircleShape),
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = tabItem,
                                    modifier = Modifier.padding(),
                                    color = if (isSelect) Color.White else Color.Black,
                                    fontSize = getNormalTextSize(),
                                )
                                Text(
                                    text = " 7",
                                    color = if (isSelect) Color.White else Color.Black,
                                    fontSize = getNormalTextSize()
                                )
                            }
                        }
                    )
                }
            }

            HorizontalPager(state = pagerState, userScrollEnabled = false, modifier = Modifier

            ) { page ->
                when (page) {
                    0 -> ListCustomerInformation()
                    1 -> {}
                    2 -> {}
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemCustomerInformation(isVisible: Boolean ,user : User){
    val sizeIcon = dimensionResource(id = R.dimen.define_dimen_16)
    val greenColor = colorResource(id = R.color.green)

    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(colorResource(id = R.color.grey))
           ) {

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top =getPaddingScreen(),start =getPaddingScreen()),
        ) {
            Image(painter = painterResource(id = user.imgAvata),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp))
            Text(
                text = user.name,
                fontSize = getSmallTextSize(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(2.dp),
            )
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                tint = greenColor,
                modifier = Modifier
                    .size(sizeIcon),
            )
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = null,
                tint = greenColor,
                modifier = Modifier

                    .size(sizeIcon),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_flag),
                contentDescription = null,
                tint = greenColor,
                modifier = Modifier
                    .size(sizeIcon),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                tint = greenColor,
                modifier = Modifier
                    .size(sizeIcon),
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "#Muanhieu",
                fontSize = 12.sp,
                modifier = Modifier.background(color = colorResource(id = R.color.grey)),
            )
            Text(
                text = "+3",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .background(color = colorResource(id = R.color.grey))
            )
            Text(
                text = "+ Tag",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 4.dp, end = 8.dp)
                    .border(
                        width = 0.5.dp,
                        color = colorResource(
                            id = R.color.grey,
                        ),
                    )
            )
        }

        ItemTimeLine(isVisible = isVisible, title = user.title[0])
        ItemTimeLine(isVisible = isVisible,title = user.title[1])
        ItemTimeLine(isVisible = true, title = user.title[2])
        Row(modifier = Modifier.padding(top = 4.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_thunder),
                tint = colorResource(id = R.color.green),
                modifier = Modifier.size(36.dp),
                contentDescription = null)
            ListSuggest(listSuggest = getListSuggest())
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(bottom = getPaddingScreen(), end = getPaddingScreen())
            .padding(
                top = getPaddingScreen(),
            )
            .height(IntrinsicSize.Min)) {
            var input by remember {
                mutableStateOf("")
            }
            Icon(painter = painterResource(id = R.drawable.ic_dot),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )

//            TextField(value = input, onValueChange = {
//                input = it
//            },
//
//                modifier = Modifier
//
//                    .height(IntrinsicSize.Min)
//                    .clip(CircleShape)
//                    .weight(1f)
//                    .padding(0.dp),
//                placeholder = {
//                    Text(
//                        "Nhập tin nhắn", style = TextStyle(
//                            color = Color.Gray
//                        ), overflow = TextOverflow.Visible
//                    )
//                },
//
//                textStyle = TextStyle(
//                    fontSize = 16.sp // Cỡ chữ của văn bản khi nhập
//                ),
//                colors = TextFieldDefaults.colors (
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    focusedContainerColor = colorResource(id = R.color.grey), // Set background color for container when focused
//                    unfocusedContainerColor = colorResource(id = R.color.grey),
//                ),
//
//            )l
            BasicTextField(
                value = input, onValueChange = { input = it },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.grey))
                    .padding(0.dp),
                decorationBox = {innerTextField->
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 12.dp),
                        // Apply padding to the Box, not to the TextField itself
                    ) {
                        Box{
                            if (input.isEmpty()) {
                                Text(
                                    text = "Nhập tin nhắn",
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    }
                },
            )

            Icon(
                imageVector = Icons.Filled.Print,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxHeight()
                    .background(colorResource(id = R.color.grey))
                    .padding(horizontal = 10.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.grey),
                ),
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.fillMaxHeight(),
                shape = RoundedCornerShape(0.dp),

                ) {
                Text(text = "Chốt đơn", color = Color.Black,
                    fontSize = getNormalTextSize(),
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemTimeLine(isVisible : Boolean,title : String) {
    var isCheckVisible by remember {
        mutableStateOf(false)
    }
    ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .padding(start = getPaddingScreen())) {
            val (ivCircle, tvTitle,rowIcons, divider) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                tint = Color.Gray,
                modifier =
                Modifier
                    .padding(top = 6.dp)
                    .size(10.dp)
                    .constrainAs(ivCircle) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
            )
            Text(text =
                buildAnnotatedString {
                    withStyle(style =
                    SpanStyle(fontSize = getNormalTextSize(),
                        color = if(isVisible) Color.Black else Color.Gray
                        )){
                        append(title)
                    }
                    withStyle(style =
                    SpanStyle(fontSize = getSmallTextSize(),color = if(isVisible) Color.Black else Color.Gray, fontWeight = FontWeight.W300)
                    ){
                        append(getRandomTime().toString())
                    }

                },

                modifier = Modifier
                    .padding(start = 4.dp, bottom = 12.dp)
                    .constrainAs(tvTitle) {
                        top.linkTo(parent.top)
                        start.linkTo(ivCircle.end)
                        end.linkTo(rowIcons.start)
                        width = Dimension.fillToConstraints
                    })

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .constrainAs(rowIcons) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Print,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .background(colorResource(id = R.color.grey))
                        .clickable {
                            isCheckVisible = !isCheckVisible
                        }
                )

                Icon(
                    imageVector = Icons.Filled.Check, // Use your check icon resource here
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .background(if (isCheckVisible) colorResource(id = R.color.grey) else Color.Transparent)
                        .let {
                            if (isCheckVisible) it else it.offset(x = 1000.dp)
                        }
                )
            }

            CustomDivider(
                color = Color.Gray,
                orientation = DividerOrientation.Vertical,
                dashGap = 0.5.dp,
                dashLength = 6.dp,
                dashThickness = 1.dp,
                modifier =
                Modifier
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        top.linkTo(ivCircle.bottom)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .width(10.dp),
            )
        }
}

@Composable
fun ItemSuggest(tag: String) {
    Text(
        text = tag,
        color = Color.Black,
        fontSize = getNormalTextSize(),
        modifier =
        Modifier
            .clip(CircleShape)
            .background(colorResource(id = R.color.white))
            .border(width = 1.dp, Color.Gray, RoundedCornerShape(30.dp))
            .padding(6.dp),
    )
}


@Composable
fun ListSuggest(listSuggest: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(listSuggest) { tag ->
            ItemSuggest(tag = tag)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListCustomerInformation(){
    LazyColumn{
        items(getListUser()){
            if(getListUser()[0] == it){
                ItemCustomerInformation(false,user = it)
            }else{
                ItemCustomerInformation(true,user = it)
            }

        }
    }
}
fun getListSuggest(): List<String> =
    listOf(
        "Cho shop xin SĐT và địa chỉ ạ",
        "Shop hỗ trợ free hôm nay ạ",
        "Bạn cần thử hàng không ạ",

    )

fun getListUser() : List<User>{
    return listOf(User(R.drawable.ic_avata,"Dương",
        listOf("Có thử hàng không shop? ","Bình luận đã xóa ","A12345 có ship ngay trong hôm nay được không em nhỉ?")),
        User(R.drawable.ic_avata2,"Nguyễn Văn Hải",
            listOf("Có thử hàng không shop? ","Shop hỗ trợ freeship không? ","A12345 có ship ngay trong hôm nay được không em nhỉ? ")),
        User(R.drawable.ic_avata1,"Hoàng Văn Tuấn",
            listOf("Có thử hàng không shop? ","Shop hỗ trợ freeship không? ","A12345 có ship ngay trong hôm nay được không em nhỉ? ")),
        User(R.drawable.ic_avata2,"Trần Văn Bảo",
            listOf("Có thử hàng không shop? ","Shop hỗ trợ freeship không? ","A12345 có ship ngay trong hôm nay được không em nhỉ? ")),
        User(R.drawable.ic_avata,"Dương",
            listOf("Có thử hàng không shop? ","Shop hỗ trợ freeship không? ","A12345 có ship ngay trong hôm nay được không em nhỉ? ")),
    )
}

enum class DividerOrientation {
    Horizontal,
    Vertical,
}

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    orientation: DividerOrientation = DividerOrientation.Horizontal,
    dashGap: Dp = 5.dp,
    dashLength: Dp = 5.dp,
    dashThickness: Dp = 3.dp,
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = dashThickness.toPx()
            val gap = dashGap.toPx()
            val length = dashLength.toPx()

            val paint =
                Paint().apply {
                    this.color = color
                    style = PaintingStyle.Stroke
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(length, gap), 0f)
                }
            if (orientation == DividerOrientation.Horizontal) {
                val centerY = size.height / 2
                drawLine(
                    color = color,
                    start = Offset(0f, centerY),
                    end = Offset(size.width, centerY),
                    strokeWidth = strokeWidth,
                    pathEffect = paint.pathEffect,
                )
            } else {
                val centerX = size.width / 2
                drawLine(
                    color = color,
                    start = Offset(centerX, 0f),
                    end = Offset(centerX, size.height),
                    strokeWidth = strokeWidth,
                    pathEffect = paint.pathEffect,
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getRandomTime(): LocalTime {
    val randomHour = Random.nextInt(0, 24)
    val randomMinute = Random.nextInt(0, 60)
    return LocalTime.of(randomHour, randomMinute)
}

@Composable
fun getNormalTextSize() : TextUnit {
    return dimensionResource(id = R.dimen.text_size_normal).value.sp
}

@Composable
fun getPaddingScreen() : Dp {
    return dimensionResource(id = R.dimen.define_dimen_12)
}

@Composable
fun getXnormalTextSize() : TextUnit {
    return dimensionResource(id = R.dimen.text_size_xnormal).value.sp
}

@Composable
fun getSmallTextSize() : TextUnit {
    return dimensionResource(id = R.dimen.text_size_small).value.sp
}