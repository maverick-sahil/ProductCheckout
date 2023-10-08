package com.example.productcheckoutsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productcheckoutsample.ui.theme.ProductCheckoutSampleTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductCheckoutSampleTheme {
//                Scaffold(
//                    topBar = {
//                        TopAppBar(
//                            title = { Text(text = "Customer List") },
//                            colors = TopAppBarDefaults.smallTopAppBarColors(
//                                containerColor = MaterialTheme.colorScheme.primary,
//                                titleContentColor = MaterialTheme.colorScheme.onPrimary
//                            )
//                        )
//                    },
//                    content = { padding ->
//                        CustomerScreen(
//                            Modifier
//                                .fillMaxSize()
//                                .padding(padding)
//                        )
//                    }
//                )
                Navigation()
            }
        }
    }
}