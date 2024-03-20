export interface Quiz {
    answer: any;
    option4: any;
    option3: any;
    option2: any;
    option1: any;
    content: any;
    quesId: any;
    qId: number;
    title: string;
    description: string;
    category: QuizCategory;
    maxMarks: number;
    numberOfQuestions: number;
  }
  
  export interface QuizCategory {
    id: number;
    title: string;
  }
  