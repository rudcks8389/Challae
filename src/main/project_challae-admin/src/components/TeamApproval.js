import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './PageTemplate.module.css';
import CommonComponent from './CommonComponent';

const Teamapproval = () => {
    const [clubs, setClubs] = useState([]);

    useEffect(() => {
        pendingClubs();
    }, []);

    const pendingClubs = async () => {
        try {
            const response = await axios.get('/api/pending');
            setClubs(response.data);
        } catch (error) {
            console.error('승인대기 클럽 조회 실패', error);
        }
    };

    const handleApprove = async (clubNum) => {
        try {
            await axios.post('/api/approve', null, { params: { clubNum } });
            setClubs(prevClubs => prevClubs.filter(club => club.clubNum !== clubNum));
        } catch (error) {
            console.error('클럽승인 오류', error);
        }
    };

    const handleReject = async (clubNum) => {
        try {
            await axios.post(`/api/reject`, null, { params: { clubNum } });
            setClubs(prevClubs => prevClubs.filter(club => club.clubNum !== clubNum));
        } catch (error) {
            console.error('클럽 거절 오류', error);
        }
    };

    return (
        <>
            <CommonComponent />
            <main className={styles["main-content"]}>
            
                <section className={styles.content}>
                    <h1 className={styles.title}>팀 승인</h1>
                    <table className={styles['team-table']}>
                        <thead>
                            <tr>
                                <th>팀 로고</th>
                                <th>신청자</th>
                                <th>신청 날짜</th>
                                <th>팀 이름</th>
                                <th>수준</th>
                                <th>지역</th>
                                <th>연락처</th>
                                <th>소개</th>
                                <th>승인 여부</th>
                            </tr>
                        </thead>
                        <tbody id="team-table-body">
                            {clubs.map((club) => (
                                <tr key={club.clubNum}>
                                    <td><img src={`/api/clublogo/${club.clubStoredPhoto}`} alt="팀 로고" /></td>
                                    <td>{club.clubPresident}</td>
                                    <td>{club.clubRegdate}</td>
                                    <td>{club.clubName}</td>
                                    <td>{club.clubLevel}</td>
                                    <td>{club.clubLocation}</td>
                                    <td>{club.clubPhone}</td>
                                    <td>{club.clubInfo}</td>
                                    <td>
                                        <button className={styles['approve-button']} onClick={() => handleApprove(club.clubNum)}>승인</button>
                                        <button className={styles['reject-button']} onClick={() => handleReject(club.clubNum)}>거절</button>
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

export default Teamapproval;
