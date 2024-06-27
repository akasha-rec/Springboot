package edu.pnu.service;

import org.springframework.stereotype.Service;

import edu.pnu.persistence.MyMemoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyMemoService {
	private final MyMemoRepository memoRepo;

}
