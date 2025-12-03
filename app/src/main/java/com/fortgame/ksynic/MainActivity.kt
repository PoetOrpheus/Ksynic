import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.BottomNavigationBar
import com.fortgame.ksynic.UI.CategoriesRow
import com.fortgame.ksynic.UI.ProductGrid
import com.fortgame.ksynic.UI.TopHeaderSection
import com.fortgame.ksynic.ui.theme.*



@Composable
fun MarketplaceScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        containerColor = BackgroundLight
    ) { paddingValues ->
        // Основной контент с прокруткой
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Верхняя секция с градиентом
            TopHeaderSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Категории (иконки)
            CategoriesRow()

            Spacer(modifier = Modifier.height(16.dp))

            // Сетка товаров
            ProductGrid()


            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}



// Для превью в Android Studio
@Preview(showBackground = true)
@Composable
fun MarketplacePreview() {
    MarketplaceScreen()
}