package com.messon.project.kmovie.ui.home.celebrity

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.messon.project.kmovie.R
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.ui.component.AppAsyncImage
import com.messon.project.kmovie.ui.component.SeeMoreHeader
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.UiModePreviews


@Composable
fun ColumnScope.CelebritiesWithTitle(
  celebrityItems: List<BasicCelebrityModel>
) {
  SeeMoreHeader(
    headerText = stringResource(R.string.popular_celebrities),
  )
  LazyRow(
    modifier = Modifier
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    contentPadding = PaddingValues(horizontal = 16.dp)
  ) {
    items(celebrityItems) { item ->
      PersonListItem(
        celebrityModel = item
      )
    }
  }
}

@Composable
private fun PersonListItem(
  celebrityModel: BasicCelebrityModel
) {
  Box(
    modifier = Modifier
      .size(100.dp)
      .border(
        width = 2.dp,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        shape = CircleShape
      ),
  ) {
    AppAsyncImage(
      modifier = Modifier.matchParentSize(),
      image = celebrityModel.profileImage,
      imageShape = CircleShape,
      errorPainter = painterResource(R.drawable.ic_person_24)
    )
  }
}

@Composable
@UiModePreviews
fun PersonTitleWithItemsPreview() {
  AppTheme {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
      Column {
        CelebritiesWithTitle(
          celebrityItems = List(2) {
            BasicCelebrityModel.fakeModel()
          }
        )
      }
    }
  }
}