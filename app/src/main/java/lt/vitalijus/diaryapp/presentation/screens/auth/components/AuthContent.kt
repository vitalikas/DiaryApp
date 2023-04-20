package lt.vitalijus.diaryapp.presentation.screens.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lt.vitalijus.diaryapp.R

@Composable
fun AuthContent(
    onButtonClicked: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(all = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(weight = 10f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = R.drawable.key),
                contentDescription = "Google Logo"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(id = R.string.auth_title),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                text = stringResource(id = R.string.auth_subtitle),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }
        Column(
            modifier = Modifier.weight(weight = 2f),
            verticalArrangement = Arrangement.Bottom
        ) {
            GoogleButton(
                onClick = onButtonClicked
            )
        }
    }
}

@Composable
@Preview
fun AuthContentPreview() {
    AuthContent()
}
