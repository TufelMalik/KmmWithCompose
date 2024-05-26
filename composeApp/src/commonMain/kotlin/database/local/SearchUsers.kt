package database.local

import comtufelmalikcomposekmm.UsersTb

class SearchUsers {

    fun execute(notes: List<UsersTb>, query: String): List<UsersTb> {
        if (query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.email.trim().lowercase().contains(query.lowercase()) ||
                    it.name.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            it.name
        }
    }
}

