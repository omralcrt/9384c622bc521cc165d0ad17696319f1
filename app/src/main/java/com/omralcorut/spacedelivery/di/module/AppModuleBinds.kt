package com.omralcorut.spacedelivery.di.module

import com.omralcorut.spacedelivery.db.RoomDatabase
import com.omralcorut.spacedelivery.db.RoomDatabaseImpl
import com.omralcorut.spacedelivery.repository.ship.ShipRepository
import com.omralcorut.spacedelivery.repository.ship.ShipRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModuleBinds {
    @Binds
    abstract fun provideRoomDatabase(impl: RoomDatabaseImpl): RoomDatabase

    @Binds
    abstract fun provideShipRepository(impl: ShipRepositoryImpl): ShipRepository
}