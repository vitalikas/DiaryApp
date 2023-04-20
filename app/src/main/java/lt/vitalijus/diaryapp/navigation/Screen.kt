package lt.vitalijus.diaryapp.navigation

import lt.vitalijus.diaryapp.navigation.ScreenConstants.AUTHENTICATION_SCREEN
import lt.vitalijus.diaryapp.navigation.ScreenConstants.HOME_SCREEN
import lt.vitalijus.diaryapp.navigation.ScreenConstants.WRITE_SCREEN
import lt.vitalijus.diaryapp.navigation.ScreenConstants.WRITE_SCREEN_ARGUMENT_KEY
import lt.vitalijus.diaryapp.navigation.ScreenConstants.WRITE_SCREEN_ARGUMENT_VALUE

sealed class Screen(val route: String) {

    object Authentication : Screen(route = AUTHENTICATION_SCREEN)

    object Home : Screen(route = HOME_SCREEN)

    object Write : Screen(
        route = "$WRITE_SCREEN?$WRITE_SCREEN_ARGUMENT_KEY={$WRITE_SCREEN_ARGUMENT_VALUE}"
    ) {
        fun passDiaryId(diaryId: String) = "$WRITE_SCREEN?$WRITE_SCREEN_ARGUMENT_KEY=$diaryId"
    }
}
