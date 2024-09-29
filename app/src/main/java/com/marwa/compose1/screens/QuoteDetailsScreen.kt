package com.marwa.compose1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marwa.compose1.DetailsViewModel

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuoteDetailsScreen() {
    val viewModel: DetailsViewModel = viewModel()
    val item = viewModel.state
    item?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(item.content, style = TextStyle(color = Color.Blue))
            Text(
                item.author,
                style = TextStyle(
                    color = Color.DarkGray,
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic
                )
            )
            Text(
                item.tags.joinToString { "#" }, style = TextStyle(
                    color = Color.Gray, fontSize = 10.sp,
                )
            )


        }
    }

}