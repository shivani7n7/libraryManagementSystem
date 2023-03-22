package seacher;

import dataAccessor.DataAccessor;
import dataAccessor.Results;
import dataAccessor.ResultsConverter;
import user.Member;

import java.util.List;

public class IDBasedMemberSearch implements MemberSearcher{
    private final String memberId;
    private final DataAccessor dataAccessor;
    public IDBasedMemberSearch(String memberId, DataAccessor dataAccessor){
        this.memberId = memberId;
        this.dataAccessor = dataAccessor;
    }


    @Override
    public List<Member> search() {
        Results rst = this.dataAccessor.getMemberByMemberId(memberId);
        ResultsConverter r = new ResultsConverter(rst);
        return r.getMembersFromResults();
    }
}
