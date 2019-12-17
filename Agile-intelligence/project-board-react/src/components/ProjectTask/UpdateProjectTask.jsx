import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { getProjectTask } from '../../actions/projectTaskAction';

function UpdateProjectTask({ getProjectTask, match }) {
  const [id, setId] = useState('');
  const [summary, setSummary] = useState('');
  const [acceptanceCriteria, setAcceptanceCriteria] = useState('');
  const [status, setStatus] = useState('');
  const [errors, setErrors] = useState({});

  const onSummaryChange = e => {
    setSummary(e.target.value);
  };

  const onAcceptanceCriteriaChange = e => {
    setAcceptanceCriteria(e.target.value);
  };

  const onStatusChange = e => {
    setStatus(e.target.value);
  };

  const onSubmit = e => {
    e.preventDefault();

    const projectTask = {
      summary,
      acceptanceCriteria,
      status
    };

    console.log(projectTask);

    setId('');
    setSummary('');
    setAcceptanceCriteria('');
    setStatus('');
  };

  /*
    componentWillReceiveProps(nextProps) {
      const {
        id,
        summary,
        acceptanceCriteria,
        status
      } = nextProps.project_task;

      setId(id);
      setSummary(summary);
      setAcceptanceCriteria(acceptanceCriteria);
      setStatus(status);
    } 
  */

  useEffect(() => {
    const { pt_id } = match.params;
    getProjectTask(pt_id);
  }, []);

  return (
    <div class='addProjectTask'>
      <div class='container'>
        <div class='row'>
          <div class='col-md-8 m-auto'>
            <a href='/' class='btn btn-light'>
              Back to Board
            </a>
            <h4 class='display-4 text-center'>Update Project Task</h4>
            <form onSubmit={onSubmit}>
              <div class='form-group'>
                <input
                  type='text'
                  class='form-control form-control-lg'
                  name='summary'
                  placeholder='Project Task summary'
                  value={summary}
                  onChange={onSummaryChange}
                />
              </div>
              <div class='form-group'>
                <textarea
                  class='form-control form-control-lg'
                  placeholder='Acceptance Criteria'
                  name='acceptanceCriteria'
                  value={acceptanceCriteria}
                  onChange={onAcceptanceCriteriaChange}
                ></textarea>
              </div>
              <div class='form-group'>
                <select
                  class='form-control form-control-lg'
                  name='status'
                  value={status}
                  onChange={onStatusChange}
                >
                  <option value=''>Select Status</option>
                  <option value='TO_DO'>TO DO</option>
                  <option value='IN_PROGRESS'>IN PROGRESS</option>
                  <option value='DONE'>DONE</option>
                </select>
              </div>
              <input type='submit' class='btn btn-primary btn-block mt-4' />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

UpdateProjectTask.propTypes = {
  project_task: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
  getProjectTask: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  project_task: state.project_task,
  errors: state.errors
});

export default connect(mapStateToProps, { getProjectTask })(UpdateProjectTask);
