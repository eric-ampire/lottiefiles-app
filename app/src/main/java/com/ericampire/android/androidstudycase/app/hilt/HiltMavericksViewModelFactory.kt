package com.ericampire.android.androidstudycase.app.hilt


import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

inline fun <reified VM : MavericksViewModel<S>, S : MavericksState> hiltMavericksViewModelFactory() =
  HiltMavericksViewModelFactory<VM, S>(VM::class.java)

class HiltMavericksViewModelFactory<VM : MavericksViewModel<S>, S : MavericksState>(
  private val viewModelClass: Class<out MavericksViewModel<S>>
) : MavericksViewModelFactory<VM, S> {

  override fun create(viewModelContext: ViewModelContext, state: S): VM {
    // We want to create the ViewModelComponent. In order to do that, we need to get its parent: ActivityComponent.
    val componentBuilder =
      EntryPoints.get(viewModelContext.app(), CreateMavericksViewModelComponent::class.java)
        .mavericksViewModelComponentBuilder()
    val viewModelComponent = componentBuilder.build()
    val viewModelFactoryMap = EntryPoints.get(
      viewModelComponent,
      HiltMavericksEntryPoint::class.java
    ).viewModelFactories
    val viewModelFactory = viewModelFactoryMap[viewModelClass]

    @Suppress("UNCHECKED_CAST")
    val castedViewModelFactory = viewModelFactory as? AssistedViewModelFactory<VM, S>
    return castedViewModelFactory?.create(state) as VM
  }
}


@MavericksViewModelScoped
@DefineComponent(parent = SingletonComponent::class)
interface MavericksViewModelComponent

@DefineComponent.Builder
interface MavericksViewModelComponentBuilder {
  fun build(): MavericksViewModelComponent
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CreateMavericksViewModelComponent {
  fun mavericksViewModelComponentBuilder(): MavericksViewModelComponentBuilder
}

@EntryPoint
@InstallIn(MavericksViewModelComponent::class)
interface HiltMavericksEntryPoint {
  val viewModelFactories: Map<Class<out MavericksViewModel<*>>, AssistedViewModelFactory<*, *>>
}