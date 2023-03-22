package com.springapp.kotlin.controller

import com.springapp.kotlin.model.Note
import com.springapp.kotlin.repository.NoteRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notes")
class NoteController(val noteRepository: NoteRepository) {

    @GetMapping("/")
    fun findAll(): MutableIterable<Note> = noteRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id: Long): ResponseEntity<Note> {
        return noteRepository.findById(id).map { note ->
            ResponseEntity.ok(note)
        }.orElse( ResponseEntity.notFound().build())
    }

    @PostMapping("/")
    fun create(@RequestBody note: Note) = noteRepository.save(note)

    @PutMapping("/{id}")
    fun update(@PathVariable(value = "id") id: Long, @RequestBody note: Note):  ResponseEntity<Note> {
        return noteRepository.findById(id).map { result ->
            val updated : Note = result.copy(title = note.title, body = note.body)

            ResponseEntity.ok().body(noteRepository.save(updated))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable(value = "id") id: Long): Unit = noteRepository.deleteById(id)
}