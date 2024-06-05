import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styles from './PageTemplate.module.css';
import CommonComponent from './CommonComponent';

const ClubManagement = () => {
    const [clubs, setClubs] = useState([]);
    const [searchKeyword, setSearchKeyword] = useState('');

    useEffect(() => {
        axios.get('/api/allClub')
            .then(response => {
                setClubs(response.data);
            })
            .catch(error => {
                console.error('클럽 정보를 가져오는 오류', error);
            });
    }, []);

    const handleSearchChange = (event) => {
        setSearchKeyword(event.target.value);
    };

    const filteredClubs = clubs.filter(club =>
        club.clubNum.includes(searchKeyword) ||
        club.clubName.includes(searchKeyword) ||
        club.clubPhone.includes(searchKeyword) ||
        club.clubLocation.includes(searchKeyword)
    );

    return (
        <>
            <CommonComponent></CommonComponent>

            <main className={styles["main-content"]}>
                <section className={styles.content}>
                    <h1 className={styles.title}>클럽 목록</h1>
                    <div className={styles.search}>
                        <input
                            type="text"
                            placeholder="검색어를 입력하세요(클럽번호, 이름, 전화번호,지역)"
                            value={searchKeyword}
                            onChange={handleSearchChange}
                        />
                    </div>
                    <table className={styles['user-table']}>
                        <thead>
                            <tr>
                                <th>사진</th>
                                <th>클럽번호</th>
                                <th>클럽이름</th>
                                <th>회장</th>
                                <th>연락처</th>
                                <th>회원수</th>
                                <th>위치</th>
                                <th>레벨</th>
                                <th>소개</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredClubs.map(club => (
                                <tr key={club.clubNum}>
                                    <td>
                                        {club.clubStoredPhoto && (
                                            <img src={`/api/clublogo/${club.clubStoredPhoto}`} alt="클럽 사진"/>
                                        )}
                                    </td>
                                    <td>{club.clubNum}</td>
                                    <td>{club.clubName}</td>
                                    <td>{club.clubPresident}</td>
                                    <td>{club.clubPhone}</td>
                                    <td>{club.clubMemberCount}</td>
                                    <td>{club.clubLocation}</td>
                                    <td>{club.clubLevel}</td>
                                    <td>{club.clubInfo}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </section>
            </main>
        </>
    );
};

export default ClubManagement;
