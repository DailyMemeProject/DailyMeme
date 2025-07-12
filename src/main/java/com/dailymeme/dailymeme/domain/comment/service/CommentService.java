package com.dailymeme.dailymeme.domain.comment.service;

import com.dailymeme.dailymeme.domain.comment.dto.CommentRequestDto;
import com.dailymeme.dailymeme.domain.comment.dto.CommentResponseDto;
import com.dailymeme.dailymeme.domain.comment.entity.Comment;
import com.dailymeme.dailymeme.domain.comment.repository.CommentRepository;
import com.dailymeme.dailymeme.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
//
//    private final CommentRepository commentRepository;
//
//    // todo 유저 검증 로직 필요
//    // todo 포스트 검증 로직 필요
//
//    /*
//    댓글 작성
//     */
//    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto) {
//        User user = UserAuthorizationUtil.getLoginUser();
//        Post post = checkFeed(feedId);
//
//        Comment comment = new Comment(
//                User,
//                post,
//                commentRequestDto.getComment()
//        );
//        Comment savecomment = commentRepository.save(comment);
//        return CommentResponseDto.toDto(savecomment);
//    }
//
//    /*
//    댓글 수정
//     */
//    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto commentRequestDto) {
//        User user = UserAuthorizationUtil.getLoginUser();
//        Post post = checkFeed(feedId);
//        Comment comment = findComment(commentId);
//
//        if (!comment.getUser().equals(user)) {
//            throw new RuntimeException("작성자만 수정할 수 있습니다.");
//        }
//
//        comment.update(commentRequestDto.getComment());
//        Comment updated = commentRepository.save(comment);
//
//        return CommentResponseDto.toDto(updated);
//    }
//
//    /*
//    댓글 조회
//     */
//    public List<CommentResponseDto> findAllComment(Long postId) {
////        Post post = postRepository.findByIdOrElseThrow(postId);
//
//        return commentRepository.findAllByPost(postId)
//                .stream()
//                .map(CommentResponseDto::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /*
//    댓글 삭제
//    */
//    public CommentResponseDto deleteComment(Long postId, Long commentId) {
//        User user = UserAuthorizationUtil.getLoginUser();
//        Post post = findPost(postId);
//        Comment comment = findComment(commentId);
//
//        if (!comment.getUser().equals(user)) {
//            throw new RuntimeException("작성자만 삭제할 수 있습니다.");
//        }
//
//        commentRepository.delete(comment);
//        return CommentResponseDto.toDto(comment);
//    }
//
//    /*
//     * 게시글 존재 확인
//     */
//    private Post findPost(Long postId) {
//        return postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
//    }
//
//    /*
//     * 댓글 존재 확인
//     */
//    private Comment findComment(Long commentId) {
//        return commentRepository.findById(commentId)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다."));
//    }
}
