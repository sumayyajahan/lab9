package com.example.lab9.service.impl;

import com.example.lab9.entity.Student;
import com.example.lab9.entity.Transcript;
import com.example.lab9.repository.TranscriptRepository;
import com.example.lab9.service.TranscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    @Autowired
    private TranscriptRepository transcriptRepository;


    @Override
    public Transcript addTranscript(Transcript newTranscript) {
        return transcriptRepository.save(newTranscript);
    }

    @Override
    public Iterable<Transcript> getAllTranscript() {
        return transcriptRepository.findAll();
    }

    @Override
    public Optional<Transcript> getStudentById(Integer transcriptId) {
        return transcriptRepository.findById(transcriptId);
    }

    @Override
    public Transcript updateTranscriptById(Integer transcriptId, Transcript editedTranscript) {
        var theTranscript = transcriptRepository.findById(transcriptId).orElse(null);
        Transcript updatedTranscript = null;
        if(theTranscript != null){

            theTranscript.setDegreeTitle(editedTranscript.getDegreeTitle());
            theTranscript.setStudent(editedTranscript.getStudent());

            updatedTranscript = transcriptRepository.save(theTranscript);
        }
        return updatedTranscript;
    }

    @Override
    public String deleteTranscriptById(Integer transcriptId) {
        var theStudent = transcriptRepository.findById(transcriptId).orElse(null);
        if(theStudent != null){
            transcriptRepository.deleteById(transcriptId);
            return String.format("Transcript is deleted", transcriptId);
        }else {
            return String.format("Transcript id is not found", transcriptId);
        }
    }
}
