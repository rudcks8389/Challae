import React from 'react';
import { Link } from "react-router-dom";
import styles from './PageTemplate.module.css';
import CommonComponent from './CommonComponent';

const MemberManagement = () => {
    return (
       
        <>
        <CommonComponent></CommonComponent>

            <main className={styles["main-content"]}>
                <section className={styles.content}>
                    <h1 className={styles.title}>회원 관리</h1>
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
                            <tr>
                                <td><img src="/img/arnold.jpg" alt="프로필 사진"/></td>
                                <td>6666</td>
                                <td>arnold</td>
                                <td>아놀드</td>
                                <td>010-1234-5678</td>
                                <td>trent@example.com</td>
                                <td>Liverpool</td>
                                <td>아니오</td>
                                <td><button className={styles['expel-button']}>퇴출</button></td>
                            </tr>
                        </tbody>
                    </table>
                </section>
            </main>
            </>
    );
}

export default MemberManagement;
