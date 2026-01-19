package fr.enssat.sharemybook.BastienLucasZakaria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

@Composable
fun BookCover(
    coverUrl: String?,
    modifier: Modifier = Modifier
) {
    val finalModifier = modifier
        .size(120.dp)
        .clip(RoundedCornerShape(8.dp))

    if (coverUrl.isNullOrBlank()) {
        // Cas où il n’y a pas de couverture
        Image(
            painter = painterResource(
                id = android.R.drawable.ic_menu_report_image
            ),
            contentDescription = "Pas de couverture",
            modifier = finalModifier,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    } else {
        // Conteneur pour superposer l'image et l'indicateur
        Box(
            modifier = finalModifier,
            contentAlignment = Alignment.Center // Centre les enfants (le CircularProgressIndicator)
        ) {
            var isLoading by remember { mutableStateOf(true) }

            // Affiche l'indicateur de chargement tant que l'image n'est pas prête
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }

            AsyncImage(
                model = coverUrl,
                contentDescription = "Couverture du livre",
                // Le modifier fillMaxSize prendra toute la place de la Box
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                onState = { state ->
                    // Met à jour l'état de chargement en fonction du résultat de Coil
                    isLoading = state is AsyncImagePainter.State.Loading
                }
            )
        }
    }
}
