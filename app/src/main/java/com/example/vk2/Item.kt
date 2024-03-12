import kotlinx.serialization.Serializable

@Serializable
class Item (val id: Int, val title: String, val description: String, val price: Int,
val discountPercentage: Double, val rating: Double, val stock: Int,
val brand: String, val category: String, val thumbnail: String, val images: Array<String>) {
}