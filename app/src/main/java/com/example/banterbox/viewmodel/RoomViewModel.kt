package com.example.banterbox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banterbox.Injection
import com.example.banterbox.data.Result.*
import com.example.banterbox.data.Room
import com.example.banterbox.data.RoomRepository
import kotlinx.coroutines.launch


class RoomViewModel : ViewModel() {

      private val _rooms = MutableLiveData<List<Room>>()
      val rooms: MutableLiveData<List<Room>> get() = _rooms
      private val roomRepository: RoomRepository
      init {
            roomRepository = RoomRepository(Injection.instance())
            loadRooms()
      }

      fun createRoom(name: String) {
            viewModelScope.launch {
                  roomRepository.createRoom(name)
            }
      }

      fun loadRooms() {
            viewModelScope.launch {
                  when (val result = roomRepository.getRooms()) {
                        is Success -> _rooms.value = result.data ?: emptyList()
                        is Error -> {

                        }
                  }
            }
      }

}

