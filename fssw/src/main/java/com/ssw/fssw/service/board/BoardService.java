package com.ssw.fssw.service.board;

import com.ssw.fssw.domain.Board;
import com.ssw.fssw.repository.board.BoardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository BoardRepository) {
        this.boardRepository = BoardRepository;
    }

    public List<Object> showBoardListPage(int offset, int limit){
        return boardRepository.findAllBoardList(offset, limit);
    }

    public long post(Board board){
        boardRepository.save(board);
        return board.getNum();
    }

    public Optional<Board> findOne(long num) {
        return boardRepository.findByNum(num);
    }
}
