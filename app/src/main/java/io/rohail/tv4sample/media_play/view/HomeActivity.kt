package io.rohail.tv4sample.media_play.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import io.rohail.tv4sample.R
import io.rohail.tv4sample.media_play.model.MediaUI
import io.rohail.tv4sample.media_play.model.MediaUiResult
import io.rohail.tv4sample.ui.theme.TV4SampleTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var openDescription by mutableStateOf<MediaUI?>(null)

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TV4SampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InitView(homeViewModel.getMedia())
                }
            }
        }

        homeViewModel.fetchMedia()
    }

    //Composable UI

    @ExperimentalFoundationApi
    @Composable
    fun InitView(itemList: Flow<MediaUiResult>) {

        var list: List<MediaUI> by remember {
            mutableStateOf(emptyList())
        }
        var isLoading by remember {
            mutableStateOf(true)
        }
        var error by remember {
            mutableStateOf("")
        }

        LaunchedEffect(Unit) {
            itemList.collectLatest { result ->
                isLoading = false
                when (result) {
                    is MediaUiResult.Success -> list = result.data
                    is MediaUiResult.Error -> error = result.errorMessage
                }
            }
        }
        ComposeLoading(isLoading)
        ComposeMedia(list)
        ComposeError(error)
        OpenDescription(openDescription)
    }

    @Composable
    private fun OpenDescription(item: MediaUI?) {
        if (item != null)
            ConstraintLayout(modifier = Modifier.background(MaterialTheme.colors.background)) {
                val (image, liveBox, description) = createRefs()
                Image(
                    contentScale = ContentScale.Crop,
                    painter =
                    rememberImagePainter(
                        data = item.imageUrl,
                        builder = {
                            placeholder(
                                R.drawable.ic_launcher_foreground
                            )
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Image",
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth()
                        .height(400.dp)

                )
                if (item.isLive)
                    Button(onClick = { },
                        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Red),
                        modifier = Modifier
                            .constrainAs(liveBox) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }) {
                        Text(text = "Live", color = Color.White)
                    }

                item.description?.let {
                    Text(text =it, modifier = Modifier.constrainAs(description){
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                    }.padding(20.dp))
                }
            }
    }

    @Composable
    private fun ComposeError(error: String) {
        if (error.isNotBlank())
            Box(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                Text(text = error, modifier = Modifier.align(Alignment.Center), color = Color.Red)
            }
    }

    @ExperimentalFoundationApi
    @Composable
    private fun ComposeMedia(list: List<MediaUI>) {
        if (list.isNotEmpty()) {
            LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
                list.forEach { mediaUI ->
                    item {
                        MediaItem(item = mediaUI)
                    }
                }
            })
        }
    }

    @Composable
    private fun MediaItem(item: MediaUI) {
        Card(
            elevation = 20.dp,
            backgroundColor = Color.Black,
            modifier =
            Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(0.dp, 20.dp, 0.dp, 0.dp))
                .height(200.dp)
                .fillMaxWidth()
                .clickable { if (openDescription == null) openDescription = item }
        ) {
            ConstraintLayout {
                val (image, liveBox) = createRefs()
                Image(
                    contentScale = ContentScale.Crop,
                    painter =
                    rememberImagePainter(
                        data = item.imageUrl,
                        builder = {
                            placeholder(
                                R.drawable.ic_launcher_foreground
                            )
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Image",
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .fillMaxSize()

                )
                if (item.isLive)
                    Button(onClick = { },
                        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Red),
                        modifier = Modifier
                            .constrainAs(liveBox) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }) {
                        Text(text = "Live", color = Color.White)
                    }
            }
        }
    }

    @Composable
    fun ComposeLoading(isLoading: Boolean) {
        if (isLoading)
            Box(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                CircularProgressIndicator(
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
    }

    override fun onBackPressed() {
        if (openDescription == null)
            super.onBackPressed()
        else
            openDescription = null
    }

}