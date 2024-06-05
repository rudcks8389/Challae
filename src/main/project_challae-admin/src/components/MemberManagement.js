import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styles from './PageTemplate.module.css';
import CommonComponent from './CommonComponent';

const MemberManagement = () => {
    const [members, setMembers] = useState([]);
    const [searchKeyword, setSearchKeyword] = useState('');

    useEffect(() => {
        axios.get('/api/allMember')
            .then(response => {
                setMembers(response.data);
            })
            .catch(error => {
                console.error('멤버 정보를 가져오는 오류', error);
            });
    }, []);

    const deleteMember = async (memberNum) => {
        try {
            console.log('삭제 요청:', memberNum);
            const response = await axios.delete('/api/deleteMember', { params: { memberNum } });
            console.log('삭제 응답:', response);
            setMembers(members.filter(member => member.memberNum !== memberNum));
        } catch (error) {
            console.error('멤버 삭제 오류', error);
        }
    };

    const handleSearchChange = (event) => {
        setSearchKeyword(event.target.value);
    };

    const filteredMembers = members.filter(member =>
        member.id.includes(searchKeyword) ||
        member.name.includes(searchKeyword) ||
        member.phone.includes(searchKeyword) ||
        member.email.includes(searchKeyword)
    );

    return (
        <>
            <CommonComponent></CommonComponent>

            <main className={styles["main-content"]}>
                <section className={styles.content}>
                    <h1 className={styles.title}>회원 관리</h1>
                    <div className={styles.search}>
                        <input
                            type="text"
                            placeholder="검색어를 입력하세요(아이디,이름,번호,이메일)"
                            value={searchKeyword}
                            onChange={handleSearchChange}
                        />
                    </div>
                    <table className={styles['user-table']}>
                        <thead>
                            <tr>
                                <th>사진</th>
                                <th>회원번호</th>
                                <th>아이디</th>
                                <th>이름</th>
                                <th>연락처</th>
                                <th>이메일</th>
                                <th>소속클럽</th>
                                <th>감독여부</th>
                                <th>퇴출여부</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredMembers.map(member => (
                                <tr key={member.memberNum}>
                                    <td>
                                        {member.storedProfile && (
                                            <img src={`/api/image/${member.storedProfile}`} alt="프로필"/>
                                        )}
                                    </td>
                                    <td>{member.memberNum}</td>
                                    <td>{member.id}</td>
                                    <td>{member.name}</td>
                                    <td>{member.phone}</td>
                                    <td>{member.email}</td>
                                    <td>{member.clubNum}</td>
                                    <td>{member.dir}</td>
                                    <td>
                                        <button 
                                            className={styles['expel-button']} 
                                            onClick={() => deleteMember(member.memberNum)}
                                        >
                                            퇴출
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </section>
            </main>
        </>
    );
};

export default MemberManagement;
