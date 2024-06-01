import styles from './PageTemplate.module.css';
import { Link } from 'react-router-dom';
import CommonComponent from './CommonComponent';

const Teamapproval = () => {

    return (
        <>
              <CommonComponent></CommonComponent>
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
                            <tr>
                                <td><img src="/img/liverpool.jpg" alt="팀 로고"/></td>
                                <td>신연재</td>
                                <td>2024-05-29</td>
                                <td>리버풀</td>
                                <td>상</td>
                                <td>서울시 중랑구</td>
                                <td>010-1234-4555</td>
                                <td>리버풀이에요!</td>
                                <td>
                            <button className={styles['approve-button']}>승인</button>
                            <button className={styles['reject-button']}>거절</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            </section>
            </main>

        </>
    );
}

export default Teamapproval;
