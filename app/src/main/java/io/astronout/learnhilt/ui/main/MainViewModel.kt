package io.astronout.learnhilt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.astronout.learnhilt.data.model.User
import io.astronout.learnhilt.data.repository.MainRepository
import io.astronout.learnhilt.utils.NetworkHelper
import io.astronout.learnhilt.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else {
                        _users.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _users.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}