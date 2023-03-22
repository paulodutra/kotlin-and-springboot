package com.springapp.kotlin.repository

import com.springapp.kotlin.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository: CrudRepository<Note, Long> {}
