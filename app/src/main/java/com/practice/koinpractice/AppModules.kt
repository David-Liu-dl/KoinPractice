package com.practice.koinpractice

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.practice.koinpractice.data.Service
import com.practice.koinpractice.data.ServiceFancyImpl
import com.practice.koinpractice.data.ServiceImpl
import com.practice.koinpractice.domain.Repository
import com.practice.koinpractice.domain.RepositoryImpl
import com.practice.koinpractice.presentation.LandingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class ViewModuleQualifier {
    LandingViewModel
}

val environmentModule: (Context) -> Module = { appContext ->
    module {
        single { appContext }
    }
}

val serviceModule = module {
    single<Service>(named("ServiceImpl")) { ServiceImpl() }
    single<Service>(named("ServiceFancyImpl")) { ServiceFancyImpl() }
}

val domainModule = module {
    single<Repository> { RepositoryImpl(get(named("ServiceFancyImpl"))) }
}

val viewModelModule = module {
    viewModel(named(ViewModuleQualifier.LandingViewModel)) { (handle: SavedStateHandle, hostName: String) ->
        LandingViewModel(handle, get(), hostName)
    }
}

val appModules: (Context) -> List<Module> = { appContext ->
    listOf(
        environmentModule(appContext),
        serviceModule,
        domainModule,
        viewModelModule
    )
}
