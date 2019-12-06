import axios from 'axios';
import { GET_ERRORS } from './types';

export const addProjectTask = (projectTask, history) => async (dispatch) => {
  try {
    await axios.post('http://localhost:8080/api/board', projectTask);
    history.push('/');
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};
