export const SET_DRAWER_OPEN = 'DRAWER_OPEN';

export function toggleDrawer(open) {
  return {type: SET_DRAWER_OPEN, drawerOpen: open};
}

export const SET_API_ENDPOINT = 'SET_ENDPOINT';

export function setApiEndpoint(url) {
  return {type: SET_API_ENDPOINT, apiEndpoint: url};
}
