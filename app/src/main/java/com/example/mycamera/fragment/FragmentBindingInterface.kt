package com.example.mycamera.fragment

/**
 * Interface for loading and data binding fragment
 * T: ViewModel class
 */
interface FragmentBindingInterface<T> {
    fun performBinding(binding: T)
}