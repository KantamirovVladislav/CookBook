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


fun readRecipesFromAssets(context: Context): List<RecipeData> {
    val assetManager = context.assets
    val recipeList = mutableListOf<RecipeData>()

    val files = assetManager.list("") ?: return emptyList()

    for (fileName in files) {
        if (fileName.endsWith(".txt")) {
            val title = fileName.removeSuffix(".txt")

            val content = readFileContentFromAssets(context, fileName)

            val imageFileName = "$title.jpg"
            val image: Painter? = if (files.contains(imageFileName)) {
                loadImageFromAssets(context, imageFileName)
            } else {
                null
            }

            if (image != null) {
                val recipe = RecipeData(title, content, image)
                recipeList.add(recipe)
            }
        }
    }

    return recipeList
}

private fun readFileContentFromAssets(context: Context, fileName: String): String {
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

private fun loadImageFromAssets(context: Context, imageFileName: String): Painter {
    val assetManager = context.assets
    val inputStream: InputStream = assetManager.open(imageFileName)


    return BitmapPainter(BitmapFactory.decodeStream(inputStream).asImageBitmap())
}
