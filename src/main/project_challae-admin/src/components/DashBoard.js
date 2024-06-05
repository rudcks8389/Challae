import styles from './PageTemplate.module.css';
import CommonComponent from './CommonComponent';
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const MemberCount = () => {
    const [memberCount, setMemberCount] = useState(0);

    useEffect(() => {
        const memberCount = async () => {
            try {
                const response = await axios.get('/api/memberCount');
                let data;
                if (typeof response.data === 'string') {
                    data = JSON.parse(response.data);
                } else {
                    data = response.data;
                }
                setMemberCount(data);
            } catch (error) {
                console.error('회원 수 정보 받기 오류:', error);
            }
        };
        memberCount();
    }, []);

    return (
        <div className={styles["stat-card"]}>
            <h3>총 회원 수</h3>
            <p id="total-posts">{memberCount}명</p>
        </div>
    );
};

const ClubCount = () => {
    const [clubCount, setClubCount] = useState(0);

    useEffect(() => {
        const clubCount = async () => {
            try {
                const response = await axios.get('/api/clubCount');
                let data;
                if (typeof response.data === 'string') {
                    data = JSON.parse(response.data);
                } else {
                    data = response.data;
                }
                setClubCount(data);
            } catch (error) {
                console.error('클럽 수 정보 받기 오류:', error);
            }
        };
        clubCount();
    }, []);

    return (
        <div className={styles["stat-card"]}>
            <h3>총 클럽 수</h3>
            <p id="total-comments">{clubCount}개</p>
        </div>
    );
};

const ArticleCount = () => {
    const [articleCount, setArticleCount] = useState(0);

    useEffect(() => {
        const articleCount = async () => {
            try {
                const response = await axios.get('/api/articleCount');
                let data;
                if (typeof response.data === 'string') {
                    data = JSON.parse(response.data);
                } else {
                    data = response.data;
                }
                setArticleCount(data);
            } catch (error) {
                console.error('게시글 수 정보 받기 오류:', error);
            }
        };
        articleCount();
    }, []);

    return (
        <div className={styles["stat-card"]}>
            <h3>총 게시글 수</h3>
            <p id="total-users">{articleCount}개</p>
        </div>
    );
};



const DashBoard = () => {
    return (
        <>
            <CommonComponent />
            <main className={styles["main-content"]}>
                <section className={styles.content}>
                    <h1 className={styles.title}>CHAL-LAE 관리자님 환영합니다!</h1>
                    <p>오늘의 우리 사이트 현황 입니다.</p>
                    <div className={styles.stats}>
                        <MemberCount />
                        <ClubCount />
                        <ArticleCount />

                    </div>
                </section>
            </main>
        </>
    );
};

export default DashBoard;
