package bd.edu.seu.namedetails.service;import bd.edu.seu.namedetails.exception.ResourseAlreadyExistException;import bd.edu.seu.namedetails.exception.ResourseDoesnotExistException;import bd.edu.seu.namedetails.exception.ResourseNotFoundException;import bd.edu.seu.namedetails.model.Member;import bd.edu.seu.namedetails.repository.MemberRepository;import org.springframework.stereotype.Service;import java.util.List;import java.util.Optional;@Servicepublic class MemberService {    private MemberRepository memberRepository;    public MemberService(MemberRepository memberRepository) {        this.memberRepository = memberRepository;    }    public List<Member> findAll(){        List<Member> memberList = memberRepository.findAll();        return memberList;    }    public Member findById(long id) throws ResourseDoesnotExistException {        Optional<Member> member = memberRepository.findById(id);        if(member.isPresent()){            return member.get();        }else {            throw new ResourseDoesnotExistException(id+"");        }    }    public List<Member> findByName(String name) throws ResourseNotFoundException {        List<Member> byNameContaining = memberRepository.findByNameContaining(name);        if(!byNameContaining.isEmpty()){            return byNameContaining;        }else{            throw new ResourseNotFoundException(name);        }    }    public Member saveMember(Member member) throws ResourseAlreadyExistException {        Optional<Member> optionalMember = memberRepository.findById(member.getA_id());        if(!optionalMember.isPresent()){            return memberRepository.save(member);        }else{            throw new ResourseAlreadyExistException(member.getA_id()+"");        }    }    public Member updateMember(Member member, long id) throws ResourseDoesnotExistException {        Optional<Member> member1 = memberRepository.findById(id);        if(member1.isPresent()){            member.setA_id(id);            return memberRepository.save(member);        }else{            throw new ResourseDoesnotExistException(id+"");        }    }    public void deleteById(long id) throws ResourseDoesnotExistException {        Optional<Member> optionalMember = memberRepository.findById(id);        if(optionalMember.isPresent()){            memberRepository.deleteById(id);        }else{            throw new ResourseDoesnotExistException(id+"");        }    }    public void deleteAll() throws ResourseNotFoundException {        if(!memberRepository.findAll().isEmpty()){            memberRepository.deleteAll();        }else{            throw new ResourseNotFoundException("empty ");        }    }}