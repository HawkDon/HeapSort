fun main() {
    val array = arrayOf(
        CustomItem(1, "Chair"),
        CustomItem(4, "Table"),
        CustomItem(6, "Fish"),
        CustomItem(7, "Pan"),
        CustomItem(2, "Shovel"),
        CustomItem(3, "Dog")
    )
    val size = array.size;

    val arraySorter = ArraySorter(array, size)

    arraySorter.dequeue()

    for (i in arraySorter.items) {
        println(i)
    }

}