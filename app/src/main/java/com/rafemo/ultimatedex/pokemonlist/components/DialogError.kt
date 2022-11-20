package com.rafemo.ultimatedex.pokemonlist.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.rafemo.ultimatedex.R
import com.rafemo.ultimatedex.ui.theme.Roboto

@Composable
fun DialogError(error: String, onRetry: () -> Unit, setShowDialog: (Boolean) -> Unit) {
    Dialog(onDismissRequest = {
        setShowDialog(false)
    }) {
        ErrorBox(
            modifier = Modifier.fillMaxWidth(0.9f),
            error = error,
            onRetry = onRetry,
            setShowDialog = setShowDialog
        )
    }
}

@Composable
fun ErrorBox(
    modifier: Modifier = Modifier,
    error: String,
    onRetry: () -> Unit,
    setShowDialog: (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = null, // decorative
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colors.primary
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

                )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Error",
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = error,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colors.background),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        setShowDialog(false)
                        onRetry()
                }) {
                    Text(
                        "Retry",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}