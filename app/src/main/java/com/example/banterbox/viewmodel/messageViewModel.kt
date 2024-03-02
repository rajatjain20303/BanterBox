package com.example.banterbox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banterbox.Injection
import com.example.banterbox.data.Message
import com.example.banterbox.data.MessageRepository
import com.example.banterbox.data.Result
import com.example.banterbox.data.User
import com.example.banterbox.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class messageViewModel:ViewModel() {


      private val messageRepository: MessageRepository
      private val userRepository: UserRepository

      init {
            messageRepository = MessageRepository(Injection.instance())
            userRepository = UserRepository(
                  FirebaseAuth.getInstance(),
                  Injection.instance()
            )
            loadCurrentUser()
      }


      private val _messages = MutableLiveData<List<Message>>()
      val messages: LiveData<List<Message>> get() = _messages

      private val _roomId = MutableLiveData<String>()
      private val _currentUser = MutableLiveData<User?>()
      val currentUser: MutableLiveData<User?> get() = _currentUser

      fun setRoomId(roomId: String) {
            _roomId.value = roomId
            loadMessages()
      }

      fun sendMessage(text: String) {
            if (_currentUser.value != null) {
                  val message = Message(
                        senderFirstName = _currentUser.value!!.firstName,
                        senderId = _currentUser.value!!.email,
                        text = text
                  )
                  viewModelScope.launch {
                        when (messageRepository.sendMessage(_roomId.value.toString(), message)) {
                              is Result.Success -> Unit
                              else -> {

                              }
                        }
                  }
            }
      }


      fun loadMessages() {
            viewModelScope.launch {
                  messageRepository.getChatMessages(_roomId.value.toString())
                        .collect { _messages.value = it }
            }
      }

      private fun loadCurrentUser() {
            viewModelScope.launch {
                  when (val result = userRepository.getCurrentUser()) {
                        is Result.Success -> _currentUser.value = result.data

                        else -> {

                        }
                  }
            }
      }
}