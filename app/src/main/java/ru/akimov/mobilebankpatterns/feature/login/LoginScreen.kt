package ru.akimov.mobilebankpatterns.feature.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.akimov.mobilebankpatterns.AppConstants
import ru.akimov.mobilebankpatterns.Destinations
import ru.akimov.mobilebankpatterns.R
import ru.akimov.mobilebankpatterns.ui.components.AppLoader
import ru.akimov.mobilebankpatterns.ui.components.AppTextField
import ru.akimov.mobilebankpatterns.ui.components.AppTopBar
import ru.akimov.mobilebankpatterns.ui.theme.MobileBankPatternsTheme

data class LoginScreenState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)

@Composable
fun LoginScreen(navController: NavController) {
    LoginScreenWidget(
        state = LoginScreenState(),
        onLoginClicked = {
            navController.navigate(Destinations.MAIN)
        }
    )
}

@Composable
private fun LoginScreenWidget(state: LoginScreenState, onLoginClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Content(state = state, onLoginClicked = onLoginClicked)
        if (state.isLoading) {
            AppLoader()
        }
    }
}

@Composable
private fun Content(
    state: LoginScreenState,
    onLoginClicked: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.entrance),
                height = AppConstants.topBarHeight,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding() + 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = state.login,
                onValueChange = {},
                placeholderText = stringResource(R.string.login)
            )
            Spacer(modifier = Modifier.height(24.dp))
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = state.password,
                onValueChange = {},
                placeholderText = stringResource(R.string.password)
            )
            Button(onClick = onLoginClicked) {
                Text(text = "Войти")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MobileBankPatternsTheme {
        LoginScreenWidget(LoginScreenState(), onLoginClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenLoadingPreview() {
    MobileBankPatternsTheme {
        LoginScreenWidget(LoginScreenState(isLoading = true), onLoginClicked = {})
    }
}