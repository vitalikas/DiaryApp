package lt.vitalijus.diaryapp.presentation.screens.auth.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import lt.vitalijus.diaryapp.R
import lt.vitalijus.diaryapp.domain.Response
import lt.vitalijus.diaryapp.presentation.screens.auth.AuthViewModel

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    icon: Int = R.drawable.google_logo,
    shape: Shape = Shapes().medium,
    borderColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderStrokeWidth: Dp = 1.dp,
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {}
) {

    val oneTapSignInResponse = viewModel.oneTapSignInResponse

    Surface(
        modifier = modifier
            .clip(shape)
            .clickable(enabled = oneTapSignInResponse != Response.Loading) {
                onClick()
            },
        shape = shape,
        border = BorderStroke(width = borderStrokeWidth, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 3000,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google logo",
                tint = Color.Unspecified
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = if (oneTapSignInResponse == Response.Loading) secondaryText else primaryText,
                fontWeight = FontWeight.Medium,
                style = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)
            )
            if (oneTapSignInResponse == Response.Loading) {
                Spacer(modifier = modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = modifier
                        .size(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }
        }
    }
}

@Composable
@Preview
fun GoogleButtonPreview() {
    GoogleButton()
}

@Composable
@Preview
fun GoogleButtonWithProgressBarPreview() {
    val oneTapSignInResponse = Response.Loading
    GoogleButton()
}
