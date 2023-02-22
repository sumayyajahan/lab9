package com.example.lab9.service;

import com.example.lab9.entity.Student;
import com.example.lab9.entity.Transcript;

import java.util.Optional;

public interface TranscriptService {

    Transcript addTranscript(Transcript newTranscript);

    Iterable<Transcript> getAllTranscript();

    Optional<Transcript> getStudentById(Integer transcriptId);



    Transcript updateTranscriptById(Integer transcriptId, Transcript editedTranscript);

    String deleteTranscriptById(Integer transcriptId);
}
