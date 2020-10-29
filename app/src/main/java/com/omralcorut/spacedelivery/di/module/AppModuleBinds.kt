package com.omralcorut.spacedelivery.di.module

import com.omralcorut.spacedelivery.db.RoomDatabase
import com.omralcorut.spacedelivery.db.RoomDatabaseImpl
import com.omralcorut.spacedelivery.repository.ship.ShipRepository
import com.omralcorut.spacedelivery.repository.ship.ShipRepositoryImpl
import com.omralcorut.spacedelivery.repository.station.StationRepository
import com.omralcorut.spacedelivery.repository.station.StationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModuleBinds {
    @Binds
    abstract fun provideRoomDatabase(impl: RoomDatabaseImpl): RoomDatabase

    @Binds
    abstract fun provideShipRepository(impl: ShipRepositoryImpl): ShipRepository

    @Binds
    abstract fun provideStationRepository(impl: StationRepositoryImpl): StationRepository
}