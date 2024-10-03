import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.cookbook.presentation.local.RecipeData
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


// Функция для чтения рецептов
fun readRecipesFromAssets(context: Context): List<RecipeData> {
    val assetManager = context.assets
    val recipeList = mutableListOf<RecipeData>()

    // Получаем список всех файлов в папке assets
    val files = assetManager.list("") ?: return emptyList()

    // Проходим по каждому .txt файлу
    for (fileName in files) {
        if (fileName.endsWith(".txt")) {
            val title = fileName.removeSuffix(".txt")

            // Читаем содержимое .txt файла
            val content = readFileContentFromAssets(context, fileName)

            // Проверяем, есть ли изображение с таким же названием
            val imageFileName = "$title.jpg"
            val image: Painter? = if (files.contains(imageFileName)) {
                loadImageFromAssets(context, imageFileName)
            } else {
                null
            }

            // Добавляем в список рецепт, если изображение найдено
            if (image != null) {
                val recipe = RecipeData(title, content, image)
                recipeList.add(recipe)
            }
        }
    }

    return recipeList
}

// Функция для чтения содержимого текстового файла
fun readFileContentFromAssets(context: Context, fileName: String): String {
    val assetManager = context.assets
    val stringBuilder = StringBuilder()

    assetManager.open(fileName).use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            var line: String? = reader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = reader.readLine()
            }
        }
    }

    return stringBuilder.toString()
}

fun loadImageFromAssets(context: Context, imageFileName: String): Painter {
    val assetManager = context.assets
    val inputStream: InputStream = assetManager.open(imageFileName)

    // Загружаем Bitmap
    val bitmap = BitmapFactory.decodeStream(inputStream)

    // Преобразуем Bitmap в ImageBitmap
    val imageBitmap: ImageBitmap = bitmap.asImageBitmap()

    // Возвращаем Painter
    return BitmapPainter(imageBitmap)
}
