interface Sort {
    fun enqueue(item: CustomItem): Unit
    fun dequeue(): CustomItem
    fun sort(event: SortOrder = SortOrder.ASCENDING): Unit
}