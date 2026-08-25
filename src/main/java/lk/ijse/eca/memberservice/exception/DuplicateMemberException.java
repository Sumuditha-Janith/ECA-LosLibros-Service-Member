package lk.ijse.eca.memberservice.exception;

public class DuplicateMemberException extends RuntimeException {

    public DuplicateMemberException(String memberId) {
        super("Member with ID '" + memberId + "' already exists");
    }
}