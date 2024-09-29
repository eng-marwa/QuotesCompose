package com.marwa.compose1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marwa.compose1.MyViewModel
import com.marwa.compose1.QuoteModel
import com.marwa.compose1.R

@Composable
fun QuotesScreen(onItemClick: (String) -> Unit) {
    val viewModel: MyViewModel = viewModel()
    LazyColumn {
        items(viewModel.state) { quote ->
            NewsItem(
                quote,
                onItemClick = { onItemClick(quote.id) },
                onClick = { viewModel.toggleFavorite(quote.id) })

        }
    }

}

@Composable
fun NewsItem(quoteModel: QuoteModel, onItemClick: (String) -> Unit, onClick: (String) -> Unit) {
    val icon = if (quoteModel.isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(5.dp), colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.DarkGray,
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(quoteModel.id) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = CenterVertically
        ) {
            NewIcon(
                Icons.Outlined.CheckCircle,
                modifier = Modifier.weight(0.15f),
                contentDescription = "quote", color = Color.Red,
            )
            Column(Modifier.weight(0.7f)) {
                Text(text = quoteModel.content, style = TextStyle(Color.Blue))
                Text(text = quoteModel.author)
            }
            NewIcon(
                icon, color = Color.DarkGray,
                modifier = Modifier.weight(0.15f),
                contentDescription = "favorite"
            ) { onClick(quoteModel.id) }

        }
    }
}

@Composable
fun NewIcon(
    icon: ImageVector, color: Color,
    modifier: Modifier,
    contentDescription: String,
    onClick: () -> Unit = {}
) {
    Image(
        imageVector = icon, colorFilter = ColorFilter.tint(color),
        contentDescription = contentDescription,
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() },
    )
}

@Composable
fun NewImage(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.baseline_location_pin_24),
        contentDescription = "",
        modifier = modifier
    )
}