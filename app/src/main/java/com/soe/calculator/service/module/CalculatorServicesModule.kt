package com.soe.calculator.service.module

import com.soe.calculator.service.Impl.CalculatorServicesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CalculatorServicesModule {

    @Binds
    abstract fun bindCalculatorServices(calculatorServicesImpl: CalculatorServicesImpl): CalculatorServices

}