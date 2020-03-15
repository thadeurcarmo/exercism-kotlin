object BinarySearch {
    tailrec fun search(list: List<Int>, item: Int, first: Int = 0, last: Int = list.size - 1): Int =
        when {
            list.isEmpty() || last < first -> throw NoSuchElementException("Element $item is not in the list")
            else -> {
                val mid = (last + first) / 2
                val elem = list[mid]
                when {
                    item == elem -> mid
                    item < elem -> search(list, item, first, mid - 1)
                    else -> search(list, item, mid + 1, last)
                }
            }
        }
}
