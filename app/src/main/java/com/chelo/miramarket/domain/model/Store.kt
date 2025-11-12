package com.chelo.miramarket.domain.model

import com.chelo.miramarket.data.model.StoreDto

data class Store(
    val id: String,
    val category: Category,
    val name: String,
    val state: StoreState,
    val description : String ,
    val ubication : String  ,
    val owner : StoreOwner,
    val schedule : List<TimeRange>,
    val image : String
)


fun Store.toDto(): StoreDto {
    return StoreDto(
        id = this.id,
        category = this.category.name,
        state = this.state.name,
        description = this.description,
        ubication = this.ubication,
        owner = this.owner.fullName,
//        schedule = this.schedule
    )
}