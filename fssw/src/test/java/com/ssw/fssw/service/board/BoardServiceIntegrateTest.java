package com.ssw.fssw.service.board;

import com.ssw.fssw.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class BoardServiceIntegrateTest {
    @Autowired
    private BoardService boardService;

    @Test
    void 글작성() {
        // given
        Board board = new Board();
        board.setTitle("spring");
        board.setContent("spring content");
        board.setType('0');
        board.setNick("nick");

        // when
        long postNum = boardService.post(board);

        //then
        Board findBoard = boardService.findOne(postNum).get();
        assertThat(board.getTitle()).isEqualTo(findBoard.getTitle());
    }
    
    @Test
    void 글목록가져오기(){
        // given
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Board board = new Board();
            board.setTitle("spring" + i);
            board.setNick("nick");
            board.setType('0');
            boards.add(board);
        }

        // when
        for (Board b : boards) {
            boardService.post(b);
        }

        // then
        int j=11;
        for (int i = 0; i < 4; i++) {
            List<Object> boardList = boardService.showBoardListPage(i*3, 3);

            System.out.println("page " + (i+1));
            for (Object object : boardList) {
                Object[] result = (Object[])object;
                System.out.println("bl = " + result[1] + " , " + result[0]);
                assertThat(result[1]).isEqualTo(boards.get(j--).getTitle());
            }
            System.out.println();
        }
    }
}