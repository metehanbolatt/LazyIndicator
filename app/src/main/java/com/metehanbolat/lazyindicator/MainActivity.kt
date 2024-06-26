package com.metehanbolat.lazyindicator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.metehanbolat.lazyindicator.ui.theme.LazyIndicatorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyIndicatorTheme {
                val items = remember {
                    listOf(
                        Item("Metehan", R.drawable.metehan),
                        Item("Oğuz", R.drawable.bedel),
                        Item("Emirhan", R.drawable.emirhan),
                        Item("Emre", R.drawable.emre),
                        Item("Halil", R.drawable.halil),
                        Item("Görkem", R.drawable.gorkem),
                        Item("Kutay", R.drawable.kutay),
                        Item("Samet", R.drawable.samet),
                        Item("Talha", R.drawable.talha),
                        Item("Hakan", R.drawable.hakan),
                        Item("Enes", R.drawable.enes),
                        Item("Kürşat", R.drawable.kursat),
                        Item("Metehan", R.drawable.metehan),
                        Item("Oğuz", R.drawable.bedel),
                        Item("Emirhan", R.drawable.emirhan),
                        Item("Emre", R.drawable.emre),
                        Item("Halil", R.drawable.halil),
                        Item("Görkem", R.drawable.gorkem),
                        Item("Kutay", R.drawable.kutay),
                        Item("Samet", R.drawable.samet),
                        Item("Talha", R.drawable.talha),
                        Item("Hakan", R.drawable.hakan),
                        Item("Enes", R.drawable.enes),
                        Item("Kürşat", R.drawable.kursat),
                        Item("Metehan", R.drawable.metehan),
                        Item("Oğuz", R.drawable.bedel),
                        Item("Emirhan", R.drawable.emirhan),
                        Item("Emre", R.drawable.emre),
                        Item("Halil", R.drawable.halil),
                        Item("Görkem", R.drawable.gorkem),
                        Item("Kutay", R.drawable.kutay),
                        Item("Samet", R.drawable.samet),
                        Item("Talha", R.drawable.talha),
                        Item("Hakan", R.drawable.hakan),
                        Item("Enes", R.drawable.enes),
                        Item("Kürşat", R.drawable.kursat),
                    )
                }
                val scope = rememberCoroutineScope()
                val listState = rememberLazyListState()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    Column {
                        ItemList(items = items, listState = listState)
                    }
                    Column(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.LightGray)
                            .padding(all = 10.dp)
                            .align(Alignment.CenterEnd),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items.forEachIndexed { index, item ->
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (index in listState.visibleItemsIndex())
                                            Color.Red else Color.Red.copy(alpha = 0.2f)
                                    )
                                    .clickable {
                                        scope.launch {
                                            listState.animateScrollToItem(index)
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

data class Item(val name: String, val image: Int)

@Composable
fun ItemList(items: List<Item>, listState: LazyListState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState
    ) {
        items(items) { item ->
            ItemRow(item)
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                painter = painterResource(id = item.image),
                contentDescription = null
            )
            Text(
                text = "${item.name}",
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}

@Composable
fun LazyListState.visibleItemsIndex(): List<Int> =
    remember {
        derivedStateOf { this.layoutInfo.visibleItemsInfo.map { it.index } }
    }.value

