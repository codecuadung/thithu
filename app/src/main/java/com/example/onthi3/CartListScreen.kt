package com.example.onthi3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun CartListScreen(catViewModel:CatViewModel = viewModel()) {
    val cats by catViewModel.cats.observeAsState(emptyList())
    LazyColumn {
        items(cats){cat ->
            CatItem(cat = cat)
        }
    }
}
@Composable
fun CatItem(cat: Cat) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { showDialog = true }
    ) {
        Text(text = cat.tags.joinToString(", "))
        AsyncImage(
            model = "https://cataas.com/cat/${cat._id}",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }

    if (showDialog) {
        CatDetailDialog(cat = cat, onDismiss = { showDialog = false })
    }
}

@Composable
fun CatDetailDialog(cat: Cat, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Cat Details") },
        text = {
            Column {
                Text(text = "id: ${cat._id}")
                Text(text = "Mimetype: ${cat.mimetype}")
                Text(text = "Size: ${cat.size}")
                Text(text = "Tags: ${cat.tags.joinToString(", ")}")
                AsyncImage(
                    model = "https://cataas.com/cat/${cat._id}",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}