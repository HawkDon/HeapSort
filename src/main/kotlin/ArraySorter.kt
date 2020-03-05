class ArraySorter(var items: Array<CustomItem>, private var size: Int) : Sort {
    override fun enqueue(item: CustomItem) {
        val newArray = items.copyOf(items.size + 1)
        newArray[newArray.size - 1] = item
        items = newArray.requireNoNulls()
        size = newArray.size
    }

    override fun dequeue(): CustomItem {
        sort(SortOrder.DESCENDING)
        val item = items[0]
        val newArray = items.sliceArray(1 until items.size)
        items = newArray
        size = newArray.size
        return item
    }

    override fun sort(event: SortOrder) {
        // Initializes heap tree
        for (i in size / 2 - 1 downTo 0) heapify(items, size, i, event)

        // Extract the root of the tree and perform heapify to create the new tree
        for (i in size - 1 downTo 0) {
            val temp = items[0]
            items[0] = items[i]
            items[i] = temp
            heapify(items, i, 0, event)
        }
    }

    private fun heapify(arr: Array<CustomItem>, n: Int, i: Int, sortOrder: SortOrder) {
        var largest = i // Largest root
        val l = 2 * i + 1 // Left
        val r = 2 * i + 2 // Right

        when (sortOrder) {
            SortOrder.ASCENDING -> {
                // Validate if left node is bigger and exists
                if (l < n && arr[l].id > arr[largest].id) largest = l

                // Validate if right node is bigger and exists
                if (r < n && arr[r].id > arr[largest].id) largest = r
            }
            SortOrder.DESCENDING -> {
                // Validate if left node is bigger and exists
                if (l < n && arr[l].id < arr[largest].id) largest = l

                // Validate if right node is bigger and exists
                if (r < n && arr[r].id < arr[largest].id) largest = r
            }
        }

        // If largest is not root anymore. Swap those two
        if (largest != i) {
            val swap = arr[i]
            arr[i] = arr[largest]
            arr[largest] = swap

            // Does it again recursively
            heapify(arr, n, largest, sortOrder);
        }
    }
}
